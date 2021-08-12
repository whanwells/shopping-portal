import { useState } from "react";
import type { VFC } from "react";
import Alert from "react-bootstrap/Alert";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
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
  const [submissionError, setSubmissionError] = useState("");

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
      const response = await request.post("/api/login", {
        body: new URLSearchParams(data),
      });
      const { token } = await response.json();
      setToken(token);
      setSubmissionError("");
    } catch (e) {
      if (e.name === "RequestError") {
        setSubmissionError("The entered credentials are invalid.");
      } else {
        setSubmissionError(e.message);
      }
      setLoading(false);
    }
  };

  return (
    <Row className="justify-content-center">
      <Col
        className="mt-2 mx-4 p-4 border rounded-lg"
        style={{ maxWidth: "36rem" }}
      >
        <h1 className="mb-3 h5 text-center">Sign in to begin shopping.</h1>
        {submissionError && (
          <Alert variant="danger">
            <Alert.Heading className="h6">Unable to sign in.</Alert.Heading>
            <p className="mb-0">
              <small>{submissionError}</small>
            </p>
          </Alert>
        )}
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
              placeholder="Enter password"
              {...register("password", { required: "Password is required." })}
              isInvalid={errors.password !== undefined}
            />
            <Form.Control.Feedback type="invalid">
              {errors?.password?.message}
            </Form.Control.Feedback>
          </Form.Group>
          <div className="text-center">
            <Button type="submit" disabled={loading}>
              Sign In
            </Button>
          </div>
        </Form>
      </Col>
    </Row>
  );
};
