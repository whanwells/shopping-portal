import type { VFC } from "react";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import { EmptyOrderList } from "./EmptyOrderList";
import { PopulatedOrderList } from "./PopulatedOrderList";

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
      <h1 className="h5 mb-3">Orders</h1>
      {data.length ? <PopulatedOrderList orders={data} /> : <EmptyOrderList />}
    </>
  );
};
