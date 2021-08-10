import type { VFC } from "react";
import { Button } from "react-bootstrap";

type ProductButtonProps = {
  id: number;
  stocked: boolean;
};

export const ProductButton: VFC<ProductButtonProps> = ({ stocked }) => {
  if (!stocked) {
    return <span>Sold Out</span>;
  }

  return <Button>Add to Cart</Button>;
};
