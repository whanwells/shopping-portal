import type { VFC } from "react";
import Alert from "react-bootstrap/Alert";
import { ShopButton } from "./ShopButton";

export const EmptyCart: VFC = () => {
  return (
    <>
      <Alert variant="info">Your shopping cart is empty.</Alert>
      <ShopButton />
    </>
  );
};
