import type { VFC, ReactNode } from "react";
import { Route, Redirect } from "react-router-dom";
import { useToken } from "../token";

type ProtectedRouteProps = {
  path: string;
  exact?: boolean;
  children: ReactNode;
};

export const ProtectedRoute: VFC<ProtectedRouteProps> = ({
  path,
  exact,
  children,
}) => {
  const { token } = useToken();

  if (!token) {
    return <Redirect to="/login" />;
  }

  return (
    <Route path={path} exact={exact}>
      {children}
    </Route>
  );
};
