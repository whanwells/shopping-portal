import type { VFC, ReactNode } from "react";
import { Route, Redirect } from "react-router-dom";
import { useToken } from "./token";

type ProtectedRouteProps = {
  path: string;
  children: ReactNode;
};

export const ProtectedRoute: VFC<ProtectedRouteProps> = ({
  path,
  children,
}) => {
  const { token } = useToken();

  if (token == null) {
    return <Redirect to="/login" />;
  }

  return <Route path={path}>{children}</Route>;
};
