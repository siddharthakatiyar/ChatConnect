import router from "next/router";
import makeAuthenticatedRequest from "./api";

export default function LogOut() {
    const url = '/api/auth/logout';
    const option = 'POST';

    const logOut = async () => {
        const response = await makeAuthenticatedRequest(url, option);
        return response;
    }

    const handleLogOut = async () => {
        await logOut();
        router.push('/auth');
    }

    localStorage.removeItem('jwtToken');
    localStorage.removeItem('validity');
    localStorage.removeItem('date');

    handleLogOut();
}
