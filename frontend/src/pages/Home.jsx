import React, { useEffect, useState } from "react";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  UploadOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Button, Layout, Menu } from "antd";
import Page1 from "./Page1";
import Page2 from "./Page2";
import Page3 from "./Page3";
import { useDispatch, useSelector } from "react-redux";
import Profile from "../components/Profile";
import { showProfile } from "../redux/profile/reducer";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import baseAPI from '../api/apiClient';
import {setUserProfile} from '../redux/userProfile/reducer'


const { Header, Sider, Content } = Layout;

const Home = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [collapsed, setCollapsed] = useState(false);
  const [selectedMenu, setSelectedMenu] = useState("1");
  const [currentUser, setCurrentUser] = useState();
  const showProfileModalValue = useSelector(
    (state) => state.profile.isShowProfile
  );


  const handleClickMenu = (e) => {
    setSelectedMenu(e.key);
  };

  const handleShowProfile = () => {
    dispatch(showProfile(true));
  };


 
  const renderContent = () => {
    const role = currentUser.role;
    if(role === 'ROLE_ADMIN'){
      switch (selectedMenu) {
        case "1":
          return <Page3  />;
        default:
          return <Page3  />;
      }
    } else{
      switch (selectedMenu) {
        case "1":
          return <Page1  />;
        case "2":
          return <Page2  />;
        case "3":
          return <Page3 />;
        default:
          return <Page1 />;
      }
    }
  };



  useEffect(() => {
    const token = Cookies.get("token");
    if (typeof token === "undefined") {
      navigate("/login");
    }
    const getUserProfile = async () => {
      try{
        const userPrl = await baseAPI.get("/user/profile")
        dispatch(setUserProfile(userPrl))
        setCurrentUser(userPrl)
      } catch(e){
        console.log(e)
      }
    }
    getUserProfile()
    // eslint-disable-next-line
  }, []);


  return (
    <>
      {showProfileModalValue ? <Profile /> : null}
      {currentUser && <Layout style={{ height: "100vh" }}>
        <Sider
          theme="light"
          trigger={null}
          width={350}
          collapsible
          collapsed={collapsed}
          collapsedWidth={100}
        >
          <div
            className="demo-logo-vertical"
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <img
              style={{
                height: "100px",
                marginBottom: "50px",
                marginTop: "50px",
              }}
              src="/ptit-logo.png"
              alt="ptit-logo"
            />
          </div>
          <Menu
            theme="light"
            mode="inline"
            defaultSelectedKeys={["1"]}
            selectedKeys={[selectedMenu]}
            onClick={handleClickMenu}
          >
           { currentUser?.role === "ROLE_USER" ? <Menu.Item key="1" icon={<UserOutlined />}>
              Điểm rèn luyện
            </Menu.Item> : ''}
            {currentUser?.role === "ROLE_ADMIN" ?  <Menu.Item key="1" icon={<UploadOutlined />}>
              Duyệt điểm rèn luyện
            </Menu.Item> : ''}
          </Menu>
        </Sider>
        <Layout>
          <Header
            style={{
              padding: 0,
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
              background: "white",
            }}
          >
            <Button
              type="text"
              icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
              onClick={() => setCollapsed(!collapsed)}
              style={{
                fontSize: "16px",
                width: 64,
                height: 64,
              }}
            />
            <img
              className="user-avatar"
              onClick={handleShowProfile}
              style={{
                width: "50px",
                height: "50px",
                borderRadius: "100%",
                marginRight: "40px",
              }}
              src="https://www.shutterstock.com/image-vector/default-avatar-profile-icon-social-600nw-1677509740.jpg"
              alt="ptit-logo-avatar"
            />
          </Header>
          <Content
            style={{
              margin: "24px 16px",
              padding: 24,
              minHeight: 280,
            }}
          >
            {currentUser && renderContent()}
          </Content>
        </Layout>
      </Layout>}
    </>
  );
};

export default Home;
