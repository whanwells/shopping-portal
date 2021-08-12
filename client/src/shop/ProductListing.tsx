import type { VFC } from "react";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { Product } from "../types";
import { CartButton } from "./CartButton";

export const ProductListing: VFC = () => {
  const { token } = useToken();

  const { data, isLoading } = useQuery("products", async () => {
    const response = await request.get("/api/products", {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <h1>Shop Products</h1>
      <ul>
        {data.map(({ id, name, msrp, stocked }: Product) => (
          <li key={id}>
            <div>{name}</div>
            <div>${msrp}</div>
            <div>
              {stocked ? (
                <CartButton productId={id} />
              ) : (
                <div>Out of Stock</div>
              )}
            </div>
          </li>
        ))}
      </ul>
    </>
  );
};
