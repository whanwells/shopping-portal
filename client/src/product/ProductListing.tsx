import type { VFC } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { useParams } from "react-router";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { Product } from "../types";
import { ProductButton } from "./ProductButton";

type ProductRouteParams = {
  category: string;
};

function titleCase(str: string) {
  return str[0].toUpperCase() + str.substr(1);
}

export const ProductListing: VFC = () => {
  const { token } = useToken();
  const { category } = useParams<ProductRouteParams>();

  const { data, isLoading } = useQuery(["products", category], async () => {
    const response = await request.get(`/api/products?category=${category}`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <Container>
      <h1 className="h4 mb-3">{titleCase(category)}</h1>
      {data[0].products.map(({ id, name, msrp, stocked }: Product) => (
        <Row key={id} className="mb-4 border-bottom">
          <Col>
            <h2 className="h6">{name}</h2>
            <p>${msrp}</p>
          </Col>
          <Col>
            <ProductButton id={id} stocked={stocked} />
          </Col>
        </Row>
      ))}
    </Container>
  );
};
