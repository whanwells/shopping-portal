import type { VFC, MouseEvent } from "react";
import Button from "react-bootstrap/Button";
import { useMutation, useQueryClient } from "react-query";
import { request } from "../request";
import { useToken } from "../token";

interface DeleteButtonProps {
  cartItemId: number;
}

export const DeleteButton: VFC<DeleteButtonProps> = ({ cartItemId }) => {
  const { token, sub } = useToken();
  const queryClient = useQueryClient();

  const mutation = useMutation(async () => {
    await request.delete(`/api/users/${sub}/cart/${cartItemId}`, { token });
    queryClient.invalidateQueries(["cart", sub]);
  });

  function handleDelete(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    mutation.mutate();
  }

  return (
    <Button variant="outline-danger" size="sm" onClick={handleDelete}>
      Delete
    </Button>
  );
};
