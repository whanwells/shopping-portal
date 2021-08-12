import type { VFC, MouseEvent } from "react";
import { Button } from "react-bootstrap";
import { useHistory } from "react-router";
import { request } from "../request";
import { useToken } from "../token";

type ProductButtonProps = {
  id: number;
  stocked: boolean;
};

export const ProductButton: VFC<ProductButtonProps> = ({ id, stocked }) => {
  const { token, sub } = useToken();
  const history = useHistory();

  if (!stocked) {
    return <span>Sold Out</span>;
  }

  async function handleClick(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();

    const body = JSON.stringify({ quantity: 1 });

    await request.put(`/api/users/${sub}/cart/${id}`, body, {
      token,
      json: true,
    });

    history.push("/cart");
  }

  return <Button onClick={handleClick}>Add to Cart</Button>;
};
