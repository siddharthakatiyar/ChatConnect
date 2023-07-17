

import router from "next/router";

const makeAuthenticatedRequest = async (url: string, option: string, data?: any) => {
  const token = localStorage.getItem('token');
  const validity = localStorage.getItem('validity');
  const date = localStorage.getItem('date');

  
  const isTokenExpired = new Date().getTime() - Number(date) > Number(validity);

  if (isTokenExpired) {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('validity');
    localStorage.removeItem('date');
    router.push('/auth');
    throw new Error('Token expired. Please log in again.');
  }

  const response = await fetch(url, {
    method: option,
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('API request failed');
  }

  return response.json();
};

export default makeAuthenticatedRequest;
