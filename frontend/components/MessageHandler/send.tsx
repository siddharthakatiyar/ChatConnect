import makeAuthenticatedRequest from "../Auth/api";

interface sendProps {
    username: string;
    message: string;
}

export default function Send(props: sendProps) {
    const { username, message } = props;
    const time = new Date();

    const sendMessage = async () => {
        const url = `/api/messages/post/${username}`
        const option = 'POST';

        const data = {
            message,
            time,
        };

        const response = await makeAuthenticatedRequest(url, option, data);
        return response;
    };

    sendMessage();
}