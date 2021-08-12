import type { VFC } from "react";
import { useQuery } from "react-query";
import { Link } from "react-router-dom";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import { Order } from "../types";

export const OrderList: VFC = () => {
  const { token, sub } = useToken();

  const { data, isLoading } = useQuery(["orderList", sub], async () => {
    const response = await request.get(`/api/users/${sub}/orders`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  if (!data.length) {
    return <div>You haven't placed any orders yet.</div>;
  }

  return (
    <>
      <h1>Orders</h1>
      <div>
        <ul>
          {data.map(({ id }: Order) => (
            <li key={id}>
              <Link to={`/orders/${id}`}>Order #{id}</Link>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};
