import type { VFC } from "react";
import { Spinner as BootstrapSpinner } from "react-bootstrap";

export const Spinner: VFC = () => (
  <div className="d-flex justify-content-center">
    <BootstrapSpinner
      animation="border"
      role="status"
      style={{ width: "3em", height: "3em" }}
    >
      <span className="sr-only">Loading...</span>
    </BootstrapSpinner>
  </div>
);
