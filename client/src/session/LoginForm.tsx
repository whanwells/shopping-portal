import { useState } from "react";
import type { VFC } from "react";
import { useForm } from "react-hook-form";
import type { SubmitHandler } from "react-hook-form";
import { Redirect } from "react-router";
import { request } from "../request";
import { useToken } from "../token";

type LoginFormInputs = {
  email: string;
  password: string;
};

export const LoginForm: VFC = () => {
  const { token, setToken } = useToken();
  const [loading, setLoading] = useState(false);

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<LoginFormInputs>();

  if (token) {
    return <Redirect to="/" />;
  }

  const onSubmit: SubmitHandler<LoginFormInputs> = async (data) => {
    setLoading(true);

    try {
      const response = await request.post(
        "/api/login",
        new URLSearchParams(data)
      );
      const { token } = await response.json();
      setToken(token);
    } catch (e) {
      console.log(e.message);
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} noValidate>
      <div>
        <label htmlFor="email">Email address</label>
        <input
          id="email"
          type="email"
          placeholder="Enter email"
          {...register("email", { required: "Email is required." })}
        />
        <div>{errors?.email?.message}</div>
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          placeholder="Password"
          {...register("password", { required: "Password is required." })}
        />
        <div>{errors?.password?.message}</div>
      </div>
      <div>
        <button type="submit" disabled={loading}>
          Sign In
        </button>
      </div>
    </form>
  );
};
