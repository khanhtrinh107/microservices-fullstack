import React, { useEffect, useState } from "react";
import { Modal, Form, Input, Select, Button , Spin, notification} from "antd";
import { LoadingOutlined } from '@ant-design/icons';
import {useSelector} from 'react-redux'
import FormData from 'form-data'
import baseAPI from '../api/apiClient'
const { TextArea } = Input;

const SubmitModal = ({ setShowSubmitModal , setIsChangeData , stompClient }) => {
  const [semesters, setSemesters] = useState();
  const [selectedSemesterId, setSelectedSemesterId] = useState();
  const [criterias, setCriterias] = useState();
  const [selectedCriteriaId, setSelectedCriteriaId] = useState(null);
  const [image, setImage] = useState();
  const [loading, setLoading] = useState(false);
  const currentUser = useSelector(state => state.userProfile)


  const handleSubmit = async (values) => {
    setLoading(true)
    const file =  await uploadImage()
    const body = {
      semesterId: values.semester,
      criteriaId: values.criteria,
      submitterId: currentUser.id,
      evidentFile: file,
      description: values.description
    }
    const response = await baseAPI.post('/task' , body)
    setLoading(false)
    console.log(response)
    if(response?.semesterId){
      notification.success({
        message: 'Thông báo',
        description: 'Nộp điểm rèn luyện thành công!',
      });
      var chatMessage = {
        receiverName: currentUser?.id,
        senderName: 'test',
        message: 'Bạn có 1 phiếu điểm rèn luyện cần duyệt!'
      };
      stompClient.send(`/app/message`, {}, JSON.stringify(chatMessage));
    } else{
      if(response.message){
        notification.error({
          message: 'Thông báo',
          description: 'Đã hết hạn nộp điểm rèn luyện cho kì học!',
        });
      } else{
        notification.error({
          message: 'Thông báo',
          description: 'Đã có lỗi xảy ra!',
        });
      }
    }
    setShowSubmitModal(false)
    setIsChangeData(true)
  };

  
  const uploadImage = async () => {
    const data = new FormData();
    data.append("file", image);
    data.append(
      "upload_preset",
      'uyir4vic'
    );
    data.append("cloud_name",'dskoc6e8s');
    data.append("folder", "Cloudinary-React");

    try {
      const response = await fetch(
        `https://api.cloudinary.com/v1_1/dskoc6e8s/image/upload`,
        {
          method: "POST",
          body: data,
        }
      );
      const res = await response.json();
      return res.url;
    } catch (error) {
      console.log(error)
    }
  };
  

  useEffect(() => {
    const getAllSemester = async () => {
      const semesterList = await baseAPI.get('/semester')
      setSemesters(semesterList)
    }
    getAllSemester()
  }, [])

  useEffect(() => {
    const getCriteriasBySemesterId = async () => {
      const criteriaList = await baseAPI.get(`/criteria/semester/${selectedSemesterId}`)
      setCriterias(criteriaList)
    }
    if (selectedSemesterId) {
      getCriteriasBySemesterId()
    }
  }, [selectedSemesterId])

  useEffect(() => {
    setCriterias([]);
    setSelectedCriteriaId(null);
  }, [selectedSemesterId]);

  return (
    
    <>
    <Modal
      okButtonProps={{ style: { background: "#ad171c" } }}
      width={800}
      style={{ padding: "40px" }}
      title="Phiếu nộp điểm rèn luyện"
      centered
      open={true}
      footer={null}
      onCancel={() => setShowSubmitModal(false)}
    >
      {loading && <Spin className="loading-submit" indicator={<LoadingOutlined style={{ fontSize: 24 }} spin />} />}
      <Form
        labelCol={{ span: 4 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        onFinish={(values) => {
          handleSubmit(values)
        }}
        style={{ width: 800 }}
      >
        <Form.Item
          label={<label style={{ color: "#ad171c" }}>Chọn học kì</label>}
          style={{ marginTop: "30px" }}
          name="semester"
          rules={[{ required: true, message: 'Bạn chưa chọn kì học!' }]}
        >
          <Select onChange={(value) => { setSelectedSemesterId(value); }} >
            {semesters?.map(semester => (
              <Select.Option key={semester?.id} value={semester?.id}>{semester?.name}</Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          label={<label style={{ color: "#ad171c" }}>Chọn tiêu chí</label>}
          name="criteria"
          rules={[{ required: true, message: 'Bạn chưa chọn tiêu chí!' }]}
        >
          <Select
            value={selectedCriteriaId}
            onChange={(value) => setSelectedCriteriaId(value)}
          >
            {criterias?.map(criteria => (
              <Select.Option key={criteria?.id} value={criteria?.id}>{criteria?.name}</Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          name="description"
          label={<label style={{ color: "#ad171c" }}>Mô tả</label>}
          rules={[{ required: true, message: 'Bạn chưa nhập mô tả!' }]}
        >
          <TextArea rows={3} />
        </Form.Item>
        <Form.Item
          name="image"
          label={<label style={{ color: "#ad171c" }}>Tải ảnh lên</label>}
          rules={[{ required: true, message: 'Bạn chưa tải ảnh lên!' }]}
        >
          <input type="file" onChange={(e) => setImage(e.target.files[0])}  />
        </Form.Item>
        <div style={{ display: "flex", marginLeft: "370px" }}>
          <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button onClick={() => setShowSubmitModal(false)}>
              Cancel
            </Button>
          </Form.Item>
          <Form.Item wrapperCol={{ offset: 8, span: 16 }} style={{ marginLeft: "50px" }}>
            <Button htmlType="submit" style={{ background: "#ad171c", color: "white" }}>
              Submit
            </Button>
          </Form.Item>
        </div>
      </Form>
    </Modal>
    </>
  );
};

export default SubmitModal;
