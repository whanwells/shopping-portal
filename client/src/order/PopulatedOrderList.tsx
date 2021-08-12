import type { VFC } from "react";
import { Link } from "react-router-dom";
import type { Order } from "../types";

type PopulatedOrderListProps = {
  orders: Order[];
};

export const PopulatedOrderList: VFC<PopulatedOrderListProps> = ({
  orders,
}) => {
  return (
    <ul>
      {orders.map(({ id }) => (
        <li key={id}>
          <Link to={`/orders/${id}`}>Order #{id}</Link>
        </li>
      ))}
    </ul>
  );
};
