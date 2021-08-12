import type { VFC, MouseEvent } from "react";
import { useHistory } from "react-router";

export const CheckoutButton: VFC = () => {
  const history = useHistory();

  function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    history.push("/checkout");
  }

  return <button onClick={handleClick}>Checkout</button>;
};
