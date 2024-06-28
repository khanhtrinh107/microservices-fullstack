import React, { useEffect, useState } from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Form, Input } from "antd";
import Cookies from "js-cookie";
import api from "../api/apiClient";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const onFinish = async (value) => {
    setLoading(true);
    try {
      const loginResponse = await api.post("auth/login", value);
      Cookies.set("token", loginResponse.jwt, { expires: 1 });
      navigate("/");
    } catch (e) {
      console.log(e);
      setError(true);
    }
    setLoading(false);
  };

  useEffect(() => {
    const token = Cookies.get("token");
    if (token) {
      navigate("/home");
    }
  });

  return (
    <div
      style={{
        height: "100vh",
        width: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        gap: "50px",
      }}
    >
      <div style={{ textAlign: "center" }}>
        <img
          src="/ptit-logo.png"
          alt="ptit"
          style={{ width: "150px", height: "180px" }}
        />
        <p
          style={{
            marginTop: "20px",
            color: "#ad171c",
            fontSize: "19px",
            fontWeight: "400",
          }}
        >
          Hệ thống nộp chấm điểm rèn luyện
        </p>
      </div>
      <Form
        style={{ height: "300px", width: "300px" }}
        name="normal_login"
        className="login-form"
        size="large"
        initialValues={{ remember: true }}
        onFinish={onFinish}
      >
        <Form.Item
          name="username"
          rules={[{ required: true, message: "Bạn chưa nhập mã sinh viên!" }]}
        >
          <Input
            prefix={<UserOutlined className="site-form-item-icon" />}
            placeholder="Mã sinh viên"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "Bạn chưa nhập mật khẩu!" }]}
        >
          <Input
            prefix={<LockOutlined className="site-form-item-icon" />}
            type="password"
            placeholder="Mật khẩu"
          />
        </Form.Item>
        {error && (
          <p style={{ color: "#ad171c", fontSize: "16px" }}>
            Sai mã sinh viên hoặc mật khẩu!
          </p>
        )}
        <Form.Item>
          <Button type="link" style={{ color: "#ad171c", padding: "0" }}>
            Quên mật khẩu
          </Button>
        </Form.Item>
        <Form.Item>
          <Button
            style={{
              background: "rgb(212, 204, 204)",
              color: "#ad171c",
              width: "100%",
              fontWeight: "500",
            }}
            loading={loading}
            type="primary"
            htmlType="submit"
            className="login-form-button"
          >
            Đăng nhập
          </Button>
        </Form.Item>
        Hoặc{" "}
        <Button type="link" style={{ color: "#ad171c" }}>
          Đăng kí!
        </Button>
      </Form>
    </div>
  );
};

export default Login;
