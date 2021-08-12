import type { VFC, MouseEvent } from "react";
import { useHistory } from "react-router";
import Button from "react-bootstrap/Button";

export const ShopButton: VFC = () => {
  const history = useHistory();

  function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    history.push("/");
  }

  return (
    <Button variant="secondary" onClick={handleClick} size="sm">
      Continue Shopping
    </Button>
  );
};
