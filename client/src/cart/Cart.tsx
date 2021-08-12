import type { VFC } from "react";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { CartItem } from "../types";

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

  if (!data.length) {
    return <div>You're cart is empty.</div>;
  }

  const total: number = data.reduce(
    (total: number, item: CartItem) =>
      total + item.product.msrp * item.quantity,
    0
  );

  return (
    <>
      <h1>Your Cart</h1>
      <ul>
        {data.map(({ product }: CartItem) => (
          <li key={product.id}>
            <div>{product.name}</div>
            <div>${product.msrp}</div>
            <div>Delete</div>
          </li>
        ))}
      </ul>

      <div>Total ${total}</div>
    </>
  );
};