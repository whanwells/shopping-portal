import type { VFC } from "react";
import { useQuery } from "react-query";
import { useParams } from "react-router";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import { OrderLine } from "../types";

type OrderDetailRouteParams = {
  orderId: string;
};

export const OrderDetail: VFC = () => {
  const { orderId } = useParams<OrderDetailRouteParams>();
  const { token, sub } = useToken();

  const { data, isLoading } = useQuery(["order", sub, orderId], async () => {
    const response = await request.get(`/api/users/${sub}/orders/${orderId}`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <h1>Order #{data.id}</h1>
      <div>
        <h2>Summary</h2>
        <div>Placed on: {data.date.substr(0, 10)}</div>
        <div>Number of items: {data.lines.length}</div>
        <div>Order total: ${data.total}</div>
      </div>
      <div>
        <h2>Items</h2>
        <ul>
          {data.lines.map(({ id, product }: OrderLine) => (
            <li key={id}>
              <div>{product.name}</div>
              <div>${product.msrp}</div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};
