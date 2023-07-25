interface Message {
    me: boolean;
    message: string;
    created_at: string;
}

interface Conversation {
    username: string;
    messageHistory: Message[];
}

interface RecentConversation {
    username: string;
}

export type { Message, Conversation, RecentConversation }