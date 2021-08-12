import type { VFC, MouseEvent } from "react";
import { useHistory } from "react-router";

export const ShopButton: VFC = () => {
  const history = useHistory();

  function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    history.push("/");
  }

  return <button onClick={handleClick}>Continue Shopping</button>;
};
