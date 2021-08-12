import { useState } from "react";
import type { VFC, MouseEvent } from "react";
import Button from "react-bootstrap/Button";
import { useMutation, useQueryClient } from "react-query";
import { useHistory } from "react-router";
import { request } from "../request";
import { useToken } from "../token";

export const CheckoutButton: VFC = () => {
  const [disabled, setDisabled] = useState(false);
  const history = useHistory();
  const queryClient = useQueryClient();
  const { token, sub } = useToken();

  const mutation = useMutation(async () => {
    const response = await request.post(`/api/users/${sub}/orders`, {
      token,
    });
    const { id } = await response.json();
    queryClient.invalidateQueries(["cart", sub]);
    history.push(`/orders/${id}`);
  });

  function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    setDisabled(true);
    mutation.mutate();
  }

  return (
    <Button size="sm" onClick={handleClick} disabled={disabled}>
      Checkout
    </Button>
  );
};
