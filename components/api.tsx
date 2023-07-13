

import router from "next/router";

const makeAuthenticatedRequest = async (url: string, options?: RequestInit) => {
    const token = localStorage.getItem('jwtToken');
    const expirationTimestamp = localStorage.getItem('jwtTokenExpiration');
  
    const isTokenExpired = expirationTimestamp && Date.now() > parseInt(expirationTimestamp, 10);
  
    if (isTokenExpired) {
      localStorage.removeItem('jwtToken');
      localStorage.removeItem('jwtTokenExpiration');
      router.push('/auth');
      throw new Error('Token expired. Please log in again.');
    }
  
    const headers = {
      Authorization: `Bearer ${token}`,
      ...options?.headers,
    };
  
    const response = await fetch(url, { ...options, headers });
  
    if (!response.ok) {
      throw new Error('API request failed');
    }
  
    return response.json();
  };
  
  export default makeAuthenticatedRequest;
  