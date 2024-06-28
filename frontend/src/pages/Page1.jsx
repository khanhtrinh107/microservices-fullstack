import React, { useEffect, useState } from "react";
import { Empty, Button , notification , Tag} from "antd";
import { List  } from "antd";
import SubmitModal from "../components/SubmitModal";
import SubmitDetail from "../components/SubmitDetail";
import baseAPI from "../api/apiClient";
import { useSelector } from "react-redux";
import {over} from 'stompjs';
import SockJS from 'sockjs-client'
var stompClient = null
const Page1 = () => {
  const [loading, setLoading] = useState(true);
  const [showSubmitDetail, setShowSubmitDetail] = useState(false);
  const [showSubmitModal, setShowSubmitModal] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);
  const [isChangeData, setIsChangeData] = useState(false);
  const [submits, setSubmits] = useState();
  const [dataSource, setDataSource] = useState();
  const profile = useSelector(state => state.userProfile)

  const onConnect = () => {
    if(profile.role === 'ROLE_USER')
      stompClient?.subscribe(`/user/${profile.id}/private`, onMessageReceive);
    else{
      stompClient?.subscribe(`/admin/public`, onAdminMessage);
    }
  }

  const renderStatus = (submit) => {
        switch(submit.status){
          case 'WAITING':
            return <Tag color="geekblue">
                      Chờ phê duyệt
                    </Tag>
          case 'APPROVE':
            return <Tag color="green">
                      Đã được duyệt
                  </Tag>
          case 'REJECTED':
            return <Tag color="volcano">
                      Bị từ chối
                  </Tag>
          default:
            return null
        }
  }

  const onError = () => {
    console.log('error')
  }

  const onAdminMessage = (payload) => {
    notification.success({
      message: 'Thông báo',
      description: 'Bạn có 1 phiếu điểm cần duyệt!',
    });
    setIsChangeData(!isChangeData)
  }

  const onMessageReceive = (payload) => {
    const bodyContent = JSON.parse(payload.body);
    notification.success({
      message: 'Thông báo',
      description: bodyContent?.message,
    });
    setIsChangeData(!isChangeData)
  }


  const connect = () => {
    const sock = new SockJS("http://localhost:8086/ws");
    stompClient = over(sock);
    stompClient.connect({} , onConnect , onError);
  }


  useEffect(() => {
    const getSubmits = async () => {
      setLoading(true)
      const submitList = await baseAPI.get(`/submit/submitter/${profile.id}`)
      setSubmits(submitList)
      const data = submitList?.map(submit => {
        return {
          id: submit?.submit.id,
          title: submit?.semester?.name,
          description: submit?.criteria?.name,
          content: submit?.submit?.description,
          status: submit?.submit?.status
        }
      })
      setDataSource(data)
      connect()
      setLoading(false)
    }
    getSubmits()
    // eslint-disable-next-line
  }, [profile , isChangeData]);

  return (
    <>
      {!submits && (
        <Empty
          style={{
            width: "100%",
            height: "100%",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        />
      )}
      <Button
        onClick={() => setShowSubmitModal(true)}
        size="midle"
        style={{
          background: "#ad171c",
          color: "white",
          margin: "20px 0 20px 0",
        }}
      >
        Nộp điểm rèn luyện
      </Button>
      {showSubmitModal && (
        <SubmitModal setShowSubmitModal={setShowSubmitModal} setIsChangeData = {setIsChangeData} stompClient={stompClient}/>
      )}
      {submits && (
        <List
        itemLayout="vertical"
        size="large"
        loading={loading}
        pagination={{
          onChange: (page) => {
          },
          pageSize: 4,
        }}
        dataSource={dataSource}
        renderItem={(item) => {
          return  <List.Item
          className="list-submut-item"
          key={item.title}
          onClick={() => {
            setSelectedItem(submits.find(s => s?.submit?.id === item?.id))
            setShowSubmitDetail(true)
          }}
          extra={
            renderStatus(item)
          }
        >
          <List.Item.Meta
            title={<p style={{color: "#ad171c"}} >{item.title}</p>}
            description={item.description}
          />
          {item.content}
        </List.Item>
        }}
      />
      )}
      {showSubmitDetail && <SubmitDetail setShowSubmitDetail={setShowSubmitDetail} selectedItem={selectedItem}></SubmitDetail>}
    </>
  );
};

export default Page1;
