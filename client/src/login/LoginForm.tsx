import type { VFC } from "react";
import { Form, Button } from "react-bootstrap";
import { useForm } from "react-hook-form";
import type { SubmitHandler } from "react-hook-form";
import { useToken } from "../auth";
import { LoginCard } from "./LoginCard";

type LoginFormInputs = {
  email: string;
  password: string;
};

export const LoginForm: VFC = () => {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<LoginFormInputs>();

  const { setToken } = useToken();

  const onSubmit: SubmitHandler<LoginFormInputs> = async (data) => {
    const response = await fetch("/api/login", {
      method: "POST",
      body: new URLSearchParams(data),
    });

    if (response.ok) {
      const { token } = await response.json();
      setToken(token);
    } else {
      console.log('access denied');
    }
  };

  return (
    <LoginCard>
      <Form onSubmit={handleSubmit(onSubmit)} noValidate>
        <Form.Group controlId="email">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            {...register("email", { required: "Email is required." })}
            isInvalid={errors.email !== undefined}
          />
          <Form.Control.Feedback type="invalid">
            {errors?.email?.message}
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            {...register("password", { required: "Password is required." })}
            isInvalid={errors.password !== undefined}
          />
          <Form.Control.Feedback type="invalid">
            {errors?.password?.message}
          </Form.Control.Feedback>
        </Form.Group>
        <div className="d-flex justify-content-center">
          <Button variant="primary" type="submit">
            Sign In
          </Button>
        </div>
      </Form>
    </LoginCard>
  );
};
