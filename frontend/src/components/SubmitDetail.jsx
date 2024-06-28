import React, { useEffect, useState } from 'react'
import { Modal, Descriptions ,Image, Button, Flex ,notification} from 'antd';
import baseAPI from '../api/apiClient';
import { useSelector } from 'react-redux';
const SubmitDetail = ({ setShowSubmitDetail, selectedItem , setIsChangeData , isChangeData , stompClient }) => {
  const [user, setUser] = useState()
  const currentUser = useSelector(state => state.userProfile)
  const handleShowModal = () => {
    setShowSubmitDetail(false)
  }
  const handleApproveSubmit = async () => {
    const reponse = await baseAPI.put(`/task/approve/${selectedItem?.submit?.id}/${currentUser?.id}`)
    if(reponse){
      notification.success({
        message: 'Thông báo',
        description: 'Duyệt phiếu điểm rèn luyện thành công!',
      });
      var chatMessage = {
        receiverName: selectedItem?.submit?.submitterId,
        senderName: 'test',
        message: 'Admin đã duyệt phiếu điểm rèn luyện của bạn!'
      };
      stompClient.send(`/user/${selectedItem?.submit?.submitterId}/private`, {}, JSON.stringify(chatMessage));
    }
    setShowSubmitDetail(false)
    setIsChangeData(!isChangeData)
  }

  const handleRejectSubmit = async () => {
    const reponse = await baseAPI.put(`/task/reject/${selectedItem?.submit?.id}/${currentUser?.id}`)
    if(reponse){
      notification.success({
        message: 'Thông báo',
        description: 'Từ chối phiếu điểm rèn luyện thành công!',
      });
      var chatMessage = {
        receiverName: selectedItem?.submit?.submitterId,
        senderName: 'test',
        message: 'Admin đã từ chối phiếu điểm rèn luyện của bạn!'
      };
      stompClient.send(`/user/${selectedItem?.submit?.submitterId}/private`, {}, JSON.stringify(chatMessage));
    }
    setIsChangeData(!isChangeData)
    setShowSubmitDetail(false)
  }


  useEffect(() => {
    const getUser = async () => {
      const createUser = await baseAPI.get(`/user/${selectedItem?.submit?.submitterId}`)
      setUser(createUser)
    }
    getUser()
  }, [selectedItem])

  return (
    <>
    <Modal
        title="Chi tiết phiếu nộp điểm rèn luyện"
        centered
        open={true}
        okText={currentUser.role === 'ROLE_ADMIN' ? 'Duyệt điểm' : 'OK'}
        okButtonProps={'danger'}
        footer={currentUser.role ==='ROLE_ADMIN' ? null : '' }
        onOk={handleShowModal}
        onCancel={handleShowModal}
      >
        <Descriptions style={{marginTop: "20px"}} layout='vertical' column={1} >
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Kì học</label>}>{selectedItem?.semester?.name}</Descriptions.Item>
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Tên sinh viên</label>}>{user?.fullName}</Descriptions.Item>
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Mã sinh viên</label>}>{user?.username}</Descriptions.Item>
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Tiêu chí</label>}>{selectedItem?.criteria?.name}</Descriptions.Item>
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Mô tả</label>}>
            {selectedItem?.submit?.description}
          </Descriptions.Item>
          <Descriptions.Item label={<label style={{ color: "#ad171c" }}>Ảnh minh chứng</label>}>
          <Image
            width={200}
            src={selectedItem.submit.evidentFile}
          />
          </Descriptions.Item>
        </Descriptions>
        {currentUser.role === 'ROLE_ADMIN' &&  <Flex wrap="wrap" gap="small" justify='end'>
          <Button onClick={handleShowModal}>
              Hủy
          </Button>
          <Button onClick={handleRejectSubmit} danger>Từ chối</Button>
          <Button onClick={handleApproveSubmit} type="primary" danger>
            Duyệt điểm
          </Button>
        </Flex>}
      </Modal>
    </>
  )
}

export default SubmitDetail