import type { VFC } from "react";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { CartItem } from "../types";
import { CheckoutButton } from "./CheckoutButton";
import { Item } from "./Item";
import { ShopButton } from "./ShopButton";

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
    return <div>Your cart is empty.</div>;
  }

  const total: number = data.reduce(
    (total: number, { product }: CartItem) => total + product.msrp,
    0
  );

  return (
    <>
      <h1>Your Cart</h1>
      <ul>
        {data.map(({ id, product }: CartItem) => (
          <li key={id}>
            <Item id={id} product={product} />
          </li>
        ))}
      </ul>
      <div>
        Total $
        {total.toLocaleString("en-US", {
          minimumFractionDigits: 2,
          maximumFractionDigits: 2,
        })}
      </div>
      <div>
        <ShopButton />
        <CheckoutButton />
      </div>
    </>
  );
};
