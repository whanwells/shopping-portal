import { QueryClient, QueryClientProvider } from "react-query";
import { Switch, Route } from "react-router-dom";
import { Cart } from "./cart";
import { Navigation, ProtectedRoute } from "./router";
import { LoginForm, Logout } from "./session";
import { CategorySelection, ProductListing } from "./shop";

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
          <CategorySelection />
        </ProtectedRoute>
        <ProtectedRoute path="/shop/:category">
          <ProductListing />
        </ProtectedRoute>
        <ProtectedRoute path="/cart">
          <Cart />
        </ProtectedRoute>
        <ProtectedRoute path="/logout">
          <Logout />
        </ProtectedRoute>
      </Switch>
    </QueryClientProvider>
  );
};

export default App;
