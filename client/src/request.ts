interface RequestOptions extends RequestInit {
  token?: string | null;
  json?: object;
}

class RequestError extends Error {
  readonly status: number;

  constructor(status: number, message: string) {
    super(message);
    this.name = "RequestError";
    this.status = status;
  }
}

export const request = async (path: string, options: RequestOptions = {}) => {
  const { token, json, ...config } = options;
  const headers: HeadersInit = {};

  if (token !== undefined && token !== null) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  if (json !== undefined) {
    headers["Content-Type"] = "application/json";
    config.body = JSON.stringify(json);
  }

  const response = await fetch(path, {
    ...config,
    headers: {
      ...config.headers,
      ...headers,
    },
  });

  if (!response.ok) {
    const message = await response.text();
    throw new RequestError(response.status, message);
  }

  return response;
};

request.get = (path: string, options: RequestOptions = {}) => {
  options.method = "GET";
  return request(path, options);
};

request.post = (path: string, options: RequestOptions = {}) => {
  options.method = "POST";
  return request(path, options);
};

request.delete = (path: string, options: RequestOptions = {}) => {
  options.method = "DELETE";
  return request(path, options);
};
