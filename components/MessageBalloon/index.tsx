import { useEffect, useState } from "react";

interface MessageBalloonProps {
  me: boolean;
  message: string;
  created_at: string;
}

export default function MessageBalloon(props: MessageBalloonProps) {
  const [time, setTime] = useState("");
  const { me, message, created_at } = props;
  const flexAlignItems = me ? "items-end" : "items-start";
  const backgroundColor = me ? "bg-[#faf0ff]" : "bg-[#7828c8]";
  const borderRounded = me ? "rounded-tr-none" : "rounded-tl-none";
  const textcolor = me ? "text-black" : "text-white";

  useEffect(() => {
    setTime(refreshTime());
  }, [])

  function refreshTime() {
    const date = new Date(created_at);
    const hours = date.getHours();
    const minutes = date.getMinutes();

    return `${hours}:${minutes}`;
  }

  return (
    <div className={`flex flex-col ${flexAlignItems} w-full h-max`}>
      <div className={`flex flex-col min-w-[5%] max-w-[65%] h-max ${backgroundColor} p-2 ${textcolor} rounded-lg ${borderRounded} mb-3`}>
        <div className="flex flex-col w-full break-words">
          <span>{message}</span>
        </div>
        <div className="flex justify-end text-xs mt-1">
          <span>{time}</span>
        </div>
      </div>
    </div>
  )
}