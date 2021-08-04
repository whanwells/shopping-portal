import type { FC } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";

export const LoginCard: FC = ({ children }) => (
  <Container>
    <Row className="justify-content-center">
      <Col xs={10} md={8} lg={6}>
        <Card className="p-4">
          <Row className="justify-content-center">
            <Col xs="auto" className="mb-3">
              <h1 className="h4">Sign in to begin shopping</h1>
            </Col>
          </Row>
          <Row className="justify-content-center">
            <Col>{children}</Col>
          </Row>
        </Card>
      </Col>
    </Row>
  </Container>
);
