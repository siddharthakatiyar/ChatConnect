import { useContext, useState } from "react";
import { ConversationContext } from "../../context/ConversationContext";
import Avatar from "../Avatar";
import { RecentConversation } from "../../types/Conversation"
import Get from "../MessageHandler/get";

interface ConversationListProps {
  isFirstConversation?: boolean;
  data: RecentConversation
  isNewChat?: boolean;
}

export default function ConversationList(props: ConversationListProps) {
  const { data } = props;
  const { isFirstConversation } = props;
  const { setConversation } = useContext(ConversationContext);
  const { username } = data;
  const borderHeight = isFirstConversation ? "0px" : "1px";
  const [ isHover, seHover ] = useState(false);
  const messageHistory = Get({ username: username });
  const messageData = {
    username: username,
    messageHistory: messageHistory
  }
  

  return (
    <div 
      className="flex items-center w-full h-[4.5rem] bg-[#f4f3f0] pl-3 pr-4 hover:bg-[#e1e0dd] cursor-pointer"
      onMouseMove={ () => seHover(true) }
      onMouseLeave={ () => seHover(false) }
      onClick={ () => setConversation(messageData) }
    >
      <div className="flex w-[4.8rem]">
        <Avatar  width="w-12" height="h-12" />
      </div>
      <div className="flex flex-col w-full">
        <hr style={{borderTop: `${borderHeight} solid rgba(120,40,200,1)`}}/>
        <div className="flex py-2">
          <div className="flex flex-col w-full h-full ">
            <span className="overflow-y-hidden text-ellipsis text-black text-base">{username}<br></br><br></br></span>
          </div>
        </div>
      </div>
    </div>
  )
}