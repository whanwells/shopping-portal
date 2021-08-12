import { Redirect } from "react-router-dom";
import { useToken } from "../token";

export const Logout = () => {
  const { setToken } = useToken();

  setToken(null);

  return <Redirect to="/login" />;
};
