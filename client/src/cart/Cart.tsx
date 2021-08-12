import type { VFC } from "react";
import { Container, Row, Col, Alert } from "react-bootstrap";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { CartItem } from "../types";

export const Cart: VFC = () => {
  const { token, sub } = useToken();

  const { data, isLoading } = useQuery(["cart", sub], async () => {
    const response = await request.get(`/api/users/${sub}/cart`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  if (!data.length) {
    return (
      <Container>
        <Alert variant="info">You're cart is empty.</Alert>
      </Container>
    );
  }

  const total: number = data.reduce(
    (total: number, item: CartItem) =>
      total + item.product.msrp * item.quantity,
    0
  );

  return (
    <Container>
      <h1 className="h4 mb-3">Your Cart</h1>
      {data.map(({ product }: CartItem) => (
        <Row key={product.id} className="mb-4 border-bottom">
          <Col>
            <h2 className="h6">{product.name}</h2>
            <p>${product.msrp}</p>
          </Col>
          <Col>Delete</Col>
        </Row>
      ))}
      <Row>
        <Col>Total</Col>
        <Col>${total}</Col>
      </Row>
    </Container>
  );
};
