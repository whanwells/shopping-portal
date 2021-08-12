import type { VFC } from "react";
import Badge from "react-bootstrap/Badge";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { Product } from "../types";
import { CartButton } from "./CartButton";
import "./ProductListing.scss";

export const ProductListing: VFC = () => {
  const { token } = useToken();

  const { data, isLoading } = useQuery("products", async () => {
    const response = await request.get("/api/products", {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <h1 className="h5 mb-3">Shop Products</h1>
      <div>
        {data.map(({ id, name, msrp, stocked }: Product) => (
          <Row key={id} className="mb-3 pb-3 align-self-center border-bottom">
            <Col xs={12} md={8}>
              <div>{name}</div>
              <div className="font-weight-light">${msrp}</div>
            </Col>
            <Col className="product-list-col-right align-self-center">
              {stocked ? (
                <CartButton productId={id} />
              ) : (
                <Badge variant="light">Out of Stock</Badge>
              )}
            </Col>
          </Row>
        ))}
      </div>
    </>
  );
};
