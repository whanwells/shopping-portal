import type { VFC } from "react";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import { EmptyCart } from "./EmptyCart";
import { PopulatedCart } from "./PopulatedCart";

export const Cart: VFC = () => {
  const { token, sub } = useToken();

  const { data, isLoading } = useQuery(["cart", sub], async () => {
    const response = await request.get(`/api/users/${sub}/cart`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <h1 className="h5 mb-3">Cart</h1>
      {data.length ? <PopulatedCart items={data} /> : <EmptyCart />}
    </>
  );
};
