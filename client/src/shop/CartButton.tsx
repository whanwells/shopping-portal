import type { VFC, MouseEvent } from "react";
import Button from "react-bootstrap/Button";
import { useMutation, useQueryClient } from "react-query";
import { useHistory } from "react-router";
import { request } from "../request";
import { useToken } from "../token";

type CartButtonProps = {
  productId: number;
};

export const CartButton: VFC<CartButtonProps> = ({ productId }) => {
  const history = useHistory();
  const queryClient = useQueryClient();
  const { token, sub } = useToken();

  const mutation = useMutation(async () => {
    await request.post(`/api/users/${sub}/cart`, {
      token,
      json: { productId },
    });
    queryClient.invalidateQueries(["cart", sub]);
    history.push("/cart");
  });

  function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    mutation.mutate();
  }

  return (
    <Button size="sm" onClick={handleClick}>
      Add to Cart
    </Button>
  );
};
