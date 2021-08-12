import { QueryClient, QueryClientProvider } from "react-query";
import { Switch, Route } from "react-router-dom";
import { Cart } from "./cart";
import { Navigation, ProtectedRoute } from "./router";
import { LoginForm } from "./login";
import { CategorySelection, ProductListing } from "./product";

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
        <ProtectedRoute path="/products/:category">
          <ProductListing />
        </ProtectedRoute>
        <ProtectedRoute path="/cart">
          <Cart />
        </ProtectedRoute>
      </Switch>
    </QueryClientProvider>
  );
};

export default App;
