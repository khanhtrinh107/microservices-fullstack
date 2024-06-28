import React, { useEffect } from "react";
import { Drawer, Button, Descriptions } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { showProfile } from "../redux/profile/reducer";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

const Profile = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const isShowProfile = useSelector((state) => state.profile.isShowProfile);
  const profile = useSelector(state => state.userProfile)
  const onClose = () => {
    dispatch(showProfile(false));
  };

  useEffect(() => {
    console.log(profile)
  } , [])

  const handleLogout = () => {
    Cookies.remove("token");
    dispatch(showProfile(false));
    navigate("/login");
  };


  return (
    <Drawer
      title="Thông tin cá nhân"
      placement={"right"}
      closable={true}
      open={isShowProfile}
      key={"right"}
      onClose={onClose}
    >
      <Descriptions layout="vertical" column={1} labelStyle={{color: "#ad171c"}}>
        <Descriptions.Item label="Mã Sinh Viên">{profile?.username}</Descriptions.Item>
        <Descriptions.Item label="Họ và tên">{profile?.fullName}</Descriptions.Item>
        <Descriptions.Item label="Địa chỉ">{profile?.address}</Descriptions.Item>
        <Descriptions.Item label="Số điện thoại">{profile?.phoneNumber}</Descriptions.Item>
        <Descriptions.Item label="Chức vụ">
          {profile?.role === 'ROLE_USER' ? 'Sinh viên' : 'Lớp trưởng'}
        </Descriptions.Item>
      </Descriptions>
      <Button
        onClick={handleLogout}
        size="midle"
        style={{ background: "#ad171c", color: "white", marginTop: "20px" }}
      >
        Đăng xuất
      </Button>
    </Drawer>
  );
};

export default Profile;
