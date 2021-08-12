import type { VFC } from "react";
import Alert from "react-bootstrap/Alert";

export const EmptyOrderList: VFC = () => {
  return (
    <>
      <Alert variant="info">Your haven't placed any orders yet.</Alert>
    </>
  );
};
