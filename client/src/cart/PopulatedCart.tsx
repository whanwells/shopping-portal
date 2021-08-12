import type { VFC } from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { CheckoutButton } from "./CheckoutButton";
import { DeleteButton } from "./DeleteButton";
import { ShopButton } from "./ShopButton";
import type { Cart } from "../types";
import "./PopulatedCart.scss";

type PopulatedCartProps = {
  items: Cart;
};

export const PopulatedCart: VFC<PopulatedCartProps> = ({ items }) => {
  const total = items.reduce(
    (total: number, { product }) => total + product.msrp,
    0
  );

  return (
    <>
      {items.map(({ id, product }) => (
        <Row key={id} className="mb-3 pb-3 align-self-center border-bottom">
          <Col xs={12} md={8}>
            <div>{product.name}</div>
            <div className="font-weight-light">${product.msrp}</div>
          </Col>
          <Col className="populated-cart-col-right align-self-center">
            <DeleteButton cartItemId={id} />
          </Col>
        </Row>
      ))}
      <Row className="mb-3 pb-3 border-bottom">
        <Col>
          <span className="font-weight-bold">
            Order total: $
            {total.toLocaleString("en-US", {
              minimumFractionDigits: 2,
              maximumFractionDigits: 2,
            })}
          </span>
        </Col>
      </Row>
      <div>
        <ShopButton /> <CheckoutButton />
      </div>
    </>
  );
};
