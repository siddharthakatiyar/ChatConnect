import { useState, useEffect } from "react";
import makeAuthenticatedRequest from "../Auth/api";

export default function Recent() {
    const [ recent, setRecent ] = useState([]);

    useEffect(() => {
        const fetchRecent = async () => {
            const response = await makeAuthenticatedRequest('/api/messages/get/recent', 'GET');
            setRecent(response);
        };

        fetchRecent();
    }, []);

    return recent;
}