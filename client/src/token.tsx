import jwtDecode from "jwt-decode";
import type { JwtPayload } from "jwt-decode";
import { createContext, useContext, useState } from "react";
import type { VFC, ReactNode } from "react";

const TOKEN_STORAGE_KEY = "token";

type TokenContextType = {
  token: string | null;
  setToken: (value: string | null) => void;
  sub?: string;
};

const TokenContext = createContext<TokenContextType>({
  token: null,
  setToken: () => {},
});

type TokenProviderProps = {
  children: ReactNode;
};

export const TokenProvider: VFC<TokenProviderProps> = ({ children }) => {
  const [token, setTokenState] = useState(
    localStorage.getItem(TOKEN_STORAGE_KEY)
  );

  const setToken = (value: string | null) => {
    if (value === null) {
      localStorage.removeItem(TOKEN_STORAGE_KEY);
    } else {
      localStorage.setItem(TOKEN_STORAGE_KEY, value);
    }
    setTokenState(value);
  };

  const sub = token ? jwtDecode<JwtPayload>(token).sub : undefined;

  return (
    <TokenContext.Provider value={{ token, setToken, sub }}>
      {children}
    </TokenContext.Provider>
  );
};

export const useToken = () => useContext(TokenContext);
