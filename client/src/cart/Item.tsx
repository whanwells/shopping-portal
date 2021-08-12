import type { VFC, MouseEvent } from "react";
import { useMutation, useQueryClient } from "react-query";
import { request } from "../request";
import { useToken } from "../token";
import type { CartItem } from "../types";

interface ItemProps extends CartItem {}

export const Item: VFC<ItemProps> = ({ id, product }) => {
  const { token, sub } = useToken();
  const queryClient = useQueryClient();

  const mutation = useMutation(async () => {
    await request.delete(`/api/users/${sub}/cart/${id}`, { token });
    queryClient.invalidateQueries(["cart", sub]);
  });

  function handleDelete(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    mutation.mutate();
  }

  return (
    <>
      <div>{product.name}</div>
      <div>${product.msrp}</div>
      <div>
        <button onClick={handleDelete}>Delete</button>
      </div>
    </>
  );
};
