import { QueryClient, QueryClientProvider } from "react-query";
import { Switch, Route } from "react-router-dom";
import { Cart } from "./cart";
import { Navigation } from "./components";
import { OrderDetail } from "./order";
import { ProtectedRoute } from "./router";
import { LoginForm, Logout } from "./session";
import { ProductListing } from "./shop";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
});

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <Navigation />
      <Switch>
        <Route path="/login">
          <LoginForm />
        </Route>
        <ProtectedRoute path="/" exact>
          <ProductListing />
        </ProtectedRoute>
        <ProtectedRoute path="/cart">
          <Cart />
        </ProtectedRoute>
        <ProtectedRoute path="/orders/:orderId">
          <OrderDetail />
        </ProtectedRoute>
        <ProtectedRoute path="/logout">
          <Logout />
        </ProtectedRoute>
      </Switch>
    </QueryClientProvider>
  );
};

export default App;
