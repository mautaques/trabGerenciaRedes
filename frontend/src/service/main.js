export async function makeGraphQLRequest(query, variables = {}, headers = {}) {
  const url = "http://localhost:8080/graphql"; // URL fixa para GraphQL

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        query: query,
        variables: variables,
      }),
    });

    if (!response.ok) {
      throw new Error(`Erro na requisição: ${response.statusText}`);
    }

    const result = await response.json();

    if (result.errors) {
      throw new Error(
        `Erro no GraphQL: ${result.errors.map((e) => e.message).join(", ")}`
      );
    }

    return result.data;
  } catch (error) {
    console.error("Erro na requisição GraphQL:", error);
    throw error;
  }
}

export async function makeRequest(method, path, data = null) {
  const url = `http://localhost:8080${path}`;

  try {
    const response = await fetch(url, {
      method: method.toUpperCase(),
      headers: {
        "Content-Type": "application/json",
        headers: {
          "Content-Type": "application/json",
        },
      },
      body: data ? JSON.stringify(data) : null,
    });

    if (!response.ok) {
      throw new Error(`Erro na requisição: ${response.statusText}`);
    }

    const text = await response.text();
    return text ? JSON.parse(text) : {};
  } catch (error) {
    console.error("Erro na requisição:", error);
    throw error;
  }
}
