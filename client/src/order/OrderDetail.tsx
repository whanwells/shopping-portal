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
      <h1 className="h5 mb-3">Order #{data.id}</h1>
      <div className="border-bottom">
        <dl className="row">
          <dt className="col-sm-4">Date</dt>
          <dd className="col-sm-8">{data.date.substr(0, 10)}</dd>
          <dt className="col-sm-4">Number of items</dt>
          <dd className="col-sm-8">{data.lines.length}</dd>
          <dt className="col-sm-4">Order total</dt>
          <dd className="col-sm-8">${data.total}</dd>
        </dl>
      </div>
      <div className="mt-4">
        <h2 className="h6">Items in this order:</h2>
        <ul>
          {data.lines.map(({ id, product }: OrderLine) => (
            <li key={id}>
              <div>{product.name}</div>
              <div className="font-weight-light">${product.msrp}</div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};
