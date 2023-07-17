import { useContext, useState } from "react";
import { ConversationContext } from "../../context/ConversationContext";
import Avatar from "../Avatar";
import { RecentConversation } from "../../types/Conversation"
import Get from "../MessageHandler/get";

interface ConversationListProps {
  isFirstConversation?: boolean;
  data: RecentConversation
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
      className="flex items-center w-full h-[4.5rem] bg-[#d1d1d1] pl-3 pr-4 hover:bg-[#2A3942] cursor-pointer"
      onMouseMove={ () => seHover(true) }
      onMouseLeave={ () => seHover(false) }
      onClick={ () => setConversation(messageData) }
    >
      <div className="flex w-[4.8rem]">
        <Avatar  width="w-12" height="h-12" />
      </div>
      <div className="flex flex-col w-full">
        <hr style={{borderTop: `${borderHeight} solid rgba(134,150,160,0.15)`}} />
        <div className="flex py-2">
          <div className="flex flex-col w-full h-full ">
            <span className="overflow-y-hidden text-ellipsis text-black text-base">{username}<br></br><br></br></span>
            {/* <span className="overflow-y-hidden text-ellipsis text-[#aebac1] text-sm">{lastMessage}</span> */}
          </div>
          <div className="flex flex-col w-auto text-[#aebac1]">
            {/* <h1 className="text-xs">{lastTime}</h1> */}
            {
              isHover ? (
                <span className="flex cursor-pointer h-full items-center justify-center">
                  <svg viewBox="0 0 19 20" width="19" height="20" className="">
                    <path fill="currentColor" d="m3.8 6.7 5.7 5.7 5.7-5.7 1.6 1.6-7.3 7.2-7.3-7.2 1.6-1.6z"></path>
                  </svg>
                </span>
              ) : null
            }
            
          </div>
        </div>
      </div>
    </div>
  )
}