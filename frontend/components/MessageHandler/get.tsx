import { useEffect, useState } from "react";
import makeAuthenticatedRequest from "../Auth/api";

interface getProps {
    username: string;
}

export default function Get(props: getProps) {
    const { username } = props;
    const [ messageHistory, setMessageHistory ] = useState([]);

    useEffect(() => {
        const fetchMessageHistory = async () => {
            const response = await makeAuthenticatedRequest(`/api/messages/get/${username}`, 'GET');
            setMessageHistory(response);
        };

        fetchMessageHistory();
    }, [username]);

    return messageHistory;
}