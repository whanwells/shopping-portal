import type { VFC } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { Link } from "react-router-dom";

const categories = ["Consoles", "Games", "Accessories"] as const;

export const CategorySelection: VFC = () => (
  <Container>
    <Row className="mb-3">
      <Col>
        <h1 className="h4">Shop Products</h1>
      </Col>
    </Row>
    <Row>
      {categories.map((category) => (
        <Col key={category}>
          <Link to={`/products/${category.toLowerCase()}`}>
            <Card
              body
              className="bg-primary text-white text-center font-weight-bold"
            >
              {category}
            </Card>
          </Link>
        </Col>
      ))}
    </Row>
  </Container>
);
