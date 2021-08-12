import type { VFC, ComponentType } from "react";
import { Route, Redirect } from "react-router-dom";
import { useToken } from "../token";

type ProtectedRouteProps = {
  path: string;
  exact?: boolean;
  component: ComponentType;
};

export const ProtectedRoute: VFC<ProtectedRouteProps> = ({
  path,
  exact,
  component,
}) => {
  const { token } = useToken();

  if (!token) {
    return <Redirect to="/login" />;
  }

  return <Route path={path} exact={exact} component={component} />;
};
