import type { VFC, MouseEvent } from "react";
import { useHistory } from "react-router";
import { request } from "../request";
import { useToken } from "../token";

type CartButtonProps = {
  productId: number;
};

export const CartButton: VFC<CartButtonProps> = ({ productId }) => {
  const { token, sub } = useToken();
  const history = useHistory();

  async function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();

    const body = JSON.stringify({ productId });

    await request.post(`/api/users/${sub}/cart`, body, {
      token,
      json: true,
    });

    history.push("/cart");
  }

  return <button onClick={handleClick}>Add to Cart</button>;
};
